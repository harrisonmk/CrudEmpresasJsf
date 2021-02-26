package com.projeto.crudempresasjsf.servico;

import com.projeto.crudempresasjsf.repositorio.EmpresaRepositorio;
import com.projeto.crudempresasjsf.modelo.Empresa;
import com.projeto.crudempresasjsf.util.Transacional;

import java.io.Serializable;

import javax.inject.Inject;

public class CadastroEmpresaServico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaRepositorio empresas;

    @Transacional
    public void salvar(Empresa empresa) {
        empresas.salvar(empresa);
    }

    @Transacional
    public void excluir(Empresa empresa) {
        empresas.excluir(empresa);
    }

}
