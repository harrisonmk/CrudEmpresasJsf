package com.projeto.crudempresasjsf.controle;

import com.projeto.crudempresasjsf.modelo.Empresa;
import com.projeto.crudempresasjsf.repositorio.EmpresaRepositorio;
import com.projeto.crudempresasjsf.util.FacesMessages;
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

    @Inject
    private FacesMessages messages;

    private List<Empresa> listaEmpresas;

    private String termoPesquisa;

    public void todasEmpresas() {
        listaEmpresas = empresas.listar();
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public void pesquisar() {
        listaEmpresas = empresas.pesquisarPorNome(termoPesquisa);

        if (listaEmpresas.isEmpty()) {
            messages.info("Sua consulta não retornou registros.");
        }
    }

    
    
}
