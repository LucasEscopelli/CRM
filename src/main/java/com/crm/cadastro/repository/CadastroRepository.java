package com.crm.cadastro.repository;

import com.crm.cadastro.model.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

    @Query("SELECT * FROM cadastro c where c.tipoCadastro = :tipoCadastro")
    List<Cadastro> findAllByTipoCadastro(@Param("tipoCadastro") String tipoCadastro);

}
