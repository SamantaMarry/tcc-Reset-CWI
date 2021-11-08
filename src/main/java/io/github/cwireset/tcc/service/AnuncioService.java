package io.github.cwireset.tcc.service;


import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.IdNaoEncontradoException;
import io.github.cwireset.tcc.exception.JaExisteAnuncioComIdImovelException;
import io.github.cwireset.tcc.repository.AnuncioRepositoryImpl;
import io.github.cwireset.tcc.request.CadastrarAnuncioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        Imovel imovel = this.imovelService.consultarImovelId(cadastrarAnuncioRequest.getImovel().getId());
        Usuario usuario = this.usuarioService.consultarUsuarioId(cadastrarAnuncioRequest.getAnunciante().getId());
        Anuncio anuncio = new Anuncio(cadastrarAnuncioRequest.getTipoAnuncio(),
                imovel, usuario, cadastrarAnuncioRequest.getValorDiaria(), cadastrarAnuncioRequest.getFormasAceitas()
                , cadastrarAnuncioRequest.getDescricao());

        return anuncio;

    }

    public Page<Anuncio> listarAnuncios(Pageable pageable) {
        return this.repository.findAll(pageable);
    }


    public Anuncio consultarAnuncioIdAnunciante(Long idAnunciante) throws Exception{
        return this.repository.findById(idAnunciante)
                .orElseThrow(() -> new IdNaoEncontradoException(idAnunciante));

    }

    public void removerAnuncio(Long id) throws Exception {
        Anuncio anuncio = consultarAnuncioIdAnunciante(id);
        repository.delete(anuncio);
    }
}
