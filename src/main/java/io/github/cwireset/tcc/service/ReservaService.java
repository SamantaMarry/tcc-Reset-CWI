package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.*;

import io.github.cwireset.tcc.repository.ReservaRepositoryImpl;
import io.github.cwireset.tcc.request.CadastrarReservaRequest;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private ReservaRepositoryImpl repository;
    private UsuarioService usuarioService;
    private AnuncioService anuncioService;

    @Autowired
    public ReservaService(ReservaRepositoryImpl repository, UsuarioService usuarioService, AnuncioService anuncioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.anuncioService = anuncioService;
    }



    public Reserva salvar(CadastrarReservaRequest cadastrarReservaRequest) throws Exception {
        Usuario solicitante = this.usuarioService.consultarUsuarioId(cadastrarReservaRequest.getIdSolicitante());
        Anuncio anuncio = this.anuncioService.consultarAnuncioId(cadastrarReservaRequest.getIdAnuncio());
        Pagamento pagamento = Pagamento.builder()
                .valorTotal(null)
                .status(StatusPagamento.PENDENTE)
                .build();
        Reserva reserva = new CadastrarReservaRequest().converterParaObjeto(cadastrarReservaRequest, anuncio,
                solicitante, pagamento);

        return this.repository.save(reserva);
    }

    public List<Reserva> consultarReservaSolicitante(Long idSolicitante) throws Exception {
        return this.repository.findBySolicitanteId(idSolicitante);

    }

    public List<Reserva> consultarReservaAnunciante(Long idAnunciante) throws Exception {
        return this.repository.findByAnuncianteId(idAnunciante);

    }


    public void pagarReserva(Long idReserva, FormaPagamento formaPagamento) {
        Optional<Reserva> reserva = this.repository.findById(idReserva);

        Pagamento pagamento = new Pagamento();
        pagamento.getValorTotal();
        pagamento.setFormaEscolhida(formaPagamento);
        pagamento.setStatus(StatusPagamento.PAGO);

        repository.save(reserva,idReserva);



    }

    public BigDecimal calcularValorReserva(Periodo periodo, Anuncio anuncio){
       LocalDateTime d1 = periodo.getDataHoraInicial();
       LocalDateTime d2 = periodo.getDataHoraFinal();

        long diffDias = ChronoUnit.DAYS.between(d1,d2);


        return valorDaReserva;






}





