package com.projeto.crudempresasjsf.repositorio;

import com.projeto.crudempresasjsf.modelo.RamoAtividade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class RamoAtividadeRepositorio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public RamoAtividadeRepositorio(EntityManager manager) {

        this.manager = manager;

    }

    public RamoAtividadeRepositorio() {

    }

    public List<RamoAtividade> pesquisarPorDescricao(String descricao) {

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<RamoAtividade> criteriaQuery = criteriaBuilder.createQuery(RamoAtividade.class);

        Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.like(root.<String>get("descricao"), descricao + "%"));

        TypedQuery<RamoAtividade> query = manager.createQuery(criteriaQuery);

        return query.getResultList();

    }

}
