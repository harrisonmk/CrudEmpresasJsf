package com.projeto.crudempresasjsf.repositorio;

import com.projeto.crudempresasjsf.modelo.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EmpresaRepositorio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public EmpresaRepositorio() {

    }

    public EmpresaRepositorio(EntityManager manager) {
        this.manager = manager;
    }

    public Empresa buscaPorId(Long id) {

        return manager.find(Empresa.class, id);

    }

    public List<Empresa> pesquisar(String nome) {

        String jpql = "from Empresa where nomeFantasia like :nomeFantasia";

        TypedQuery<Empresa> query = manager.createQuery(jpql, Empresa.class);
        query.setParameter("nomeFantasia", nome + "%");

        return query.getResultList();
    }

    public Empresa salvar(Empresa empresa) {

        return manager.merge(empresa);

    }

    public void excluir(Empresa empresa) {

        empresa = buscaPorId(empresa.getId());
        manager.remove(empresa);

    }

    public List<Empresa> listar() {
        String jpql = "from Empresa";
        TypedQuery<Empresa> query = manager.createQuery(jpql, Empresa.class);

        return query.getResultList();
    }

}
