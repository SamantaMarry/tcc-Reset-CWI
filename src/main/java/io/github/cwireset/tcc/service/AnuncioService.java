package io.github.cwireset.tcc.service;


import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.StatusAnuncio;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.IdNaoEncontradoException;
import io.github.cwireset.tcc.repository.AnuncioRepositoryImpl;
import io.github.cwireset.tcc.request.CadastrarAnuncioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {

    private AnuncioRepositoryImpl repository;
    private UsuarioService usuarioService;
    private ImovelService imovelService;

    @Autowired
    public AnuncioService(AnuncioRepositoryImpl repository, UsuarioService usuarioService, ImovelService imovelService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.imovelService = imovelService;
    }

    public Anuncio anunciarImovel(CadastrarAnuncioRequest cadastrarAnuncioRequest) throws Exception {
        Imovel imovelAnunciado = this.imovelService.consultarImovelId(cadastrarAnuncioRequest.getIdImovel());
        Usuario anunciante = this.usuarioService.consultarUsuarioId(cadastrarAnuncioRequest.getIdAnunciante());
        Anuncio anuncio = new CadastrarAnuncioRequest().converterParaObjeto(cadastrarAnuncioRequest, imovelAnunciado,
                anunciante);

        return this.repository.save(anuncio);
    }


    public Page<Anuncio> listarAnuncios(Pageable pageable) {
        return this.repository.findByStatus(StatusAnuncio.ATIVO, pageable);

    }

    public Anuncio consultarAnuncioId(Long id){
        return this.repository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException(id));
    }


    public List<Anuncio> consultarAnuncioIdAnunciante(Long idAnunciante) throws Exception{
        return this.repository.findByAnuncianteIdAndStatus(idAnunciante, StatusAnuncio.ATIVO);
    }

    public void removerAnuncio(Long id)  {
        Anuncio anuncio = consultarAnuncioId(id);
        anuncio.setStatus(StatusAnuncio.INATIVO);
        repository.save(anuncio);

    }
}
