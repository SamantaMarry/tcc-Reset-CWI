package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.IdNaoEncontradoException;
import io.github.cwireset.tcc.repository.ImovelRepositoryImpl;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ImovelService {

    private ImovelRepositoryImpl repository;
    private UsuarioService usuarioService;

    @Autowired
    public ImovelService(ImovelRepositoryImpl repository, UsuarioService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    public Imovel cadastrarImovel(CadastrarImovelRequest cadastrarImovelRequest) throws Exception {
        Usuario usuario =
                this.usuarioService.consultarUsuarioId(cadastrarImovelRequest.getProprietario().getId());
        Imovel imovel = new CadastrarImovelRequest().converterParaObjeto(cadastrarImovelRequest, usuario);
        return this.repository.save(imovel);
    }

    public Page<Imovel> listarImoveis(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Imovel consultarImovelProprietario(Long idProprietario){
        return this.repository.findById(idProprietario)
                .orElseThrow(() -> new IdNaoEncontradoException(idProprietario));

    }

    public Imovel consultarImovelId(Long id) throws Exception{
        return this.repository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException(id));

    }

    public void removerImovel(Long id) throws Exception{
        Imovel imovel = consultarImovelId(id);

        if (!repository.existsById(id)){
            throw new IdNaoEncontradoException(id);
        }
        this.repository.delete(imovel);

    }


}
