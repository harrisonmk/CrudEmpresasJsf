package com.projeto.crudempresasjsf.controle;

import com.projeto.crudempresasjsf.modelo.Empresa;
import com.projeto.crudempresasjsf.repositorio.EmpresaRepositorio;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import javax.inject.Named;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaRepositorio empresas;

    private List<Empresa> listaEmpresas;

    public void todasEmpresas() {
        listaEmpresas = empresas.listar();
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    
    
}
