package io.github.cwireset.tcc.repository;


import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepositoryImpl extends PagingAndSortingRepository<Imovel, Long> {


   // CadastrarImovelRequest findByIdProprietario(Long idProprietario);
}
