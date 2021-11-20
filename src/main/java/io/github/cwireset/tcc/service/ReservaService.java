package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.*;

import io.github.cwireset.tcc.exception.*;
import io.github.cwireset.tcc.repository.AnuncioRepositoryImpl;
import io.github.cwireset.tcc.repository.ReservaRepositoryImpl;
import io.github.cwireset.tcc.request.CadastrarReservaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservaService {

    private ReservaRepositoryImpl repository;
    private UsuarioService usuarioService;
    private AnuncioService anuncioService;
    private AnuncioRepositoryImpl anuncioRepository;

    @Autowired
    public ReservaService(ReservaRepositoryImpl repository, UsuarioService usuarioService, AnuncioService anuncioService, AnuncioRepositoryImpl anuncioRepository) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.anuncioService = anuncioService;
        this.anuncioRepository = anuncioRepository;
    }

    public Reserva salvar(CadastrarReservaRequest cadastrarReservaRequest) throws Exception {
        Usuario solicitante = this.usuarioService.consultarUsuarioId(cadastrarReservaRequest.getIdSolicitante());
        Anuncio anuncio = this.anuncioService.consultarAnuncioId(cadastrarReservaRequest.getIdAnuncio());
        Pagamento pagamento = Pagamento.builder()
                .valorTotal(calcularValorReserva(cadastrarReservaRequest.getPeriodo(), anuncio))
                .status(StatusPagamento.PENDENTE)
                .build();

        Reserva reserva = new CadastrarReservaRequest().converterParaObjeto(cadastrarReservaRequest, anuncio,
                solicitante, pagamento);

        LocalDate dataChegada = cadastrarReservaRequest.getPeriodo().getDataHoraInicial().toLocalDate();
        LocalDate dataSaida = cadastrarReservaRequest.getPeriodo().getDataHoraFinal().toLocalDate();
        LocalTime horaEntrada = LocalTime.of(14, 00, 00);
        LocalTime horaSaida = LocalTime.of(12,00,00);
        cadastrarReservaRequest.getPeriodo().setDataHoraInicial(LocalDateTime.of(dataChegada,horaEntrada));
        cadastrarReservaRequest.getPeriodo().setDataHoraFinal(LocalDateTime.of(dataSaida,horaSaida));

        if(reserva.getPeriodo().getDataHoraFinal().isBefore(reserva.getPeriodo().getDataHoraInicial())){
            throw new DataFinalDaReservaMiorQueDataInicialException();
        }

        LocalDate d1 = reserva.getPeriodo().getDataHoraInicial().toLocalDate();
        LocalDate d2 = reserva.getPeriodo().getDataHoraFinal().toLocalDate();
        long diffDias = ChronoUnit.DAYS.between(d1, d2);

        if(anuncio.getImovel().getTipoImovel().equals(TipoImovel.POUSADA) && diffDias < 5){
            throw new QuantidadeMinimaDeDiariasException();
        }
        if (anuncio.getImovel().getTipoImovel().equals(TipoImovel.HOTEL) && reserva.getQuantidadePessoas() < 2){
            throw new QuantidadeMinimaDePessoasException();
        }
        if (reserva.getSolicitante() == anuncio.getAnunciante()){
            throw new SolicitanteMesmoQueAnuncianteException();
        }

        List<Reserva> reservasMesmoPeriodo = repository.findAllValidSobreposicaodeDatas(
                anuncio.getImovel().getId(),
                cadastrarReservaRequest.getPeriodo().getDataHoraInicial(),
                cadastrarReservaRequest.getPeriodo().getDataHoraFinal());
        if (reservasMesmoPeriodo.size() != 0){
            throw new ReservaComSobreposicaoDatasException();
        }

        return this.repository.save(reserva);
    }


    public List<Reserva> consultarReservaSolicitante(Long idSolicitante) throws Exception {
        return this.repository.findBySolicitanteId(idSolicitante);

    }

    public List<Reserva> consultarReservaAnunciante(Long idAnunciante) throws Exception {
        return this.repository.findByAnuncianteId(idAnunciante);

    }

    public void pagarReserva(Long idReserva, FormaPagamento formaPagamento) throws Exception {
        Reserva reserva = this.repository.findById(idReserva)
                .orElseThrow(() -> new IdReservaNãoLocalizadaException(idReserva));

        boolean formaPagamentoAceita = anuncioRepository.existsByFormasAceitas(formaPagamento);

        if (!reserva.getPagamento().getFormaEscolhida().equals(formaPagamentoAceita)) {
            throw new FormaPagamentoNãoCeitaPeloAnuncioException(reserva.getPagamento().getFormaEscolhida(),
                    reserva.getAnuncio().getFormasAceitas());
        }

        if (!reserva.getPagamento().getStatus().equals(StatusPagamento.PAGO)){
            throw new NãoPodeRealizarPagamentoException();
        }

        if (reserva.getPagamento().getStatus().equals(StatusPagamento.PENDENTE)){
            reserva.getPagamento().setStatus(StatusPagamento.PAGO);
            repository.save(reserva);
        }

    }

    public void cancelarReserva(Long idReserva) {
        Reserva reserva = this.repository.findById(idReserva)
                .orElseThrow(() -> new IdReservaNãoLocalizadaException(idReserva));

        if (reserva.getPagamento().getStatus() != StatusPagamento.PENDENTE){
            throw new NãoPodeRealizarCancelamentoException();
        }

        reserva.getPagamento().setStatus(StatusPagamento.CANCELADO);
        repository.save(reserva);
    }

    public void estornarReserva(Long idReserva) {

        Reserva reserva = this.repository.findById(idReserva)
                .orElseThrow(() -> new IdReservaNãoLocalizadaException(idReserva));


        if (reserva.getPagamento().getStatus() != StatusPagamento.PAGO){
            throw new NãoPodeRealizarEstornoException();
        }

        reserva.getPagamento().setStatus(StatusPagamento.ESTORNADO);
        repository.save(reserva);

    }

    public BigDecimal calcularValorReserva(Periodo periodo, Anuncio anuncio) {

        LocalDate d1 = periodo.getDataHoraInicial().toLocalDate();
        LocalDate d2 = periodo.getDataHoraFinal().toLocalDate();
        long diffDias = ChronoUnit.DAYS.between(d1, d2);
        BigDecimal valorDiaria = new BigDecimal(String.valueOf(anuncio.getValorDiaria()));
        BigDecimal valorDaReserva = valorDiaria.multiply(BigDecimal.valueOf(diffDias));

        return valorDaReserva;

    }

}