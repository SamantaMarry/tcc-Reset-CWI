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

import java.util.List;


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
        Usuario proprietario = this.usuarioService.consultarUsuarioId(cadastrarImovelRequest.getIdProprietario());
        Imovel imovel = new CadastrarImovelRequest().converterParaObjeto(cadastrarImovelRequest, proprietario);
        return this.repository.save(imovel);
    }

    public Page<Imovel> listarImoveis(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public List<Imovel> consultarImovelProprietario(Long idProprietario) throws Exception {
        return this.repository.findByProprietarioId(idProprietario);

    }

    public Imovel consultarImovelId(Long idImovel) throws Exception{
        return this.repository.findById(idImovel)
                .orElseThrow(() -> new IdNaoEncontradoException(idImovel));

    }

    public void removerImovel(Long idImovel) throws Exception{
        Imovel imovel = consultarImovelId(idImovel);

        if (!repository.existsById(idImovel)){
            throw new IdNaoEncontradoException(idImovel);
        }
        this.repository.delete(imovel);

    }


}
