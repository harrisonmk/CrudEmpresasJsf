package com.projeto.crudempresasjsf.controle;

import com.projeto.crudempresasjsf.modelo.Empresa;
import com.projeto.crudempresasjsf.modelo.RamoAtividade;
import com.projeto.crudempresasjsf.modelo.TipoEmpresa;
import com.projeto.crudempresasjsf.repositorio.EmpresaRepositorio;
import com.projeto.crudempresasjsf.repositorio.RamoAtividadeRepositorio;
import com.projeto.crudempresasjsf.servico.CadastroEmpresaServico;
import com.projeto.crudempresasjsf.util.FacesMessages;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.convert.Converter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaRepositorio empresas;

    @Inject
    private FacesMessages messages;

    @Inject
    private RamoAtividadeRepositorio ramoAtividades;

    @Inject
    private CadastroEmpresaServico cadastroEmpresaService;

    private List<Empresa> listaEmpresas;

    private String termoPesquisa;

    private Converter ramoAtividadeConverter;

    private Empresa empresa;

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

    public TipoEmpresa[] getTiposEmpresa() {
        return TipoEmpresa.values();
    }

    public Converter getRamoAtividadeConverter() {
        return ramoAtividadeConverter;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    private boolean jaHouvePesquisa() {
        return termoPesquisa != null && !"".equals(termoPesquisa);
    }

    
    
    public void pesquisar() {
        listaEmpresas = empresas.pesquisarPorNome(termoPesquisa);

        if (listaEmpresas.isEmpty()) {
            messages.info("Sua consulta não retornou registros.");
        }
    }

    
    
    public List<RamoAtividade> completarRamoAtividade(String termo) {
        List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisarPorDescricao(termo);

        ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);

        return listaRamoAtividades;
    }

    
    
    public void prepararNovaEmpresa() {
        empresa = new Empresa();
    }

    
    
    public void salvar() {
        cadastroEmpresaService.salvar(empresa);

        if (jaHouvePesquisa()) {
            pesquisar();
        } else {
            todasEmpresas();
        }

        messages.info("Empresa cadastrada com sucesso!");

        PrimeFaces.current().ajax().update(Arrays.asList("frm:empresasDataTable", "frm:messages"));

    }

    
    
    public void excluir() {
        cadastroEmpresaService.excluir(empresa);

        empresa = null;

        atualizarRegistros();

        messages.info("Empresa excluída com sucesso!");
    }

    
    
    private void atualizarRegistros() {
        if (jaHouvePesquisa()) {
            pesquisar();
        } else {
            todasEmpresas();
        }
    }

    
    
    public boolean isEmpresaSeleciona() {
        return empresa != null && empresa.getId() != null;
    }

    
    
    public void prepararEdicao() {

        ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));

    }
    
    

}
