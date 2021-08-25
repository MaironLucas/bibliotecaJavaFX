/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mairo
 */
@Entity
@Table(name = "emprestimo")
@NamedQueries({
    @NamedQuery(name = "Emprestimo.findAll", query = "SELECT e FROM Emprestimo e"),
    @NamedQuery(name = "Emprestimo.findByIDEmprestimo", query = "SELECT e FROM Emprestimo e WHERE e.iDEmprestimo = :iDEmprestimo"),
    @NamedQuery(name = "Emprestimo.findByDataEmprestimo", query = "SELECT e FROM Emprestimo e WHERE e.dataEmprestimo = :dataEmprestimo"),
    @NamedQuery(name = "Emprestimo.findByDataDevolucao", query = "SELECT e FROM Emprestimo e WHERE e.dataDevolucao = :dataDevolucao"),
    @NamedQuery(name = "Emprestimo.findEmprestados", query = "SELECT e FROM Emprestimo e WHERE (e.iDUsuario.nome LIKE :nome OR e.iDUsuario.numDoc = :numDoc OR e.iDLivro.titulo LIKE :titulo OR e.iDLivro.isbn = :isbn) AND e.dataDevolucao IS NULL"),
})
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEmprestimo")
    private Integer iDEmprestimo;
    @Basic(optional = false)
    @Column(name = "DataEmprestimo")
    @Temporal(TemporalType.DATE)
    private Date dataEmprestimo;
    @Column(name = "DataDevolucao")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;
    @Basic(optional = false)
    @Column(name = "Prazo")
    @Temporal(TemporalType.DATE)
    private Date prazo;
    @Basic(optional = false)
    @Column(name = "TempoDeEmprestimo")
    private int tempoDeEmprestimo;
    @Column(name = "Observacoes")
    private String observacoes;
    @JoinColumn(name = "IDLivro", referencedColumnName = "IDLivro")
    @ManyToOne(optional = false)
    private Livro iDLivro;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;

    public Emprestimo() {
    }

    public Emprestimo(Integer iDEmprestimo) {
        this.iDEmprestimo = iDEmprestimo;
    }

    public Emprestimo(Integer iDEmprestimo, Date dataEmprestimo, Date prazo, int tempoDeEmprestimo) {
        this.iDEmprestimo = iDEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
        this.prazo = prazo;
        this.tempoDeEmprestimo = tempoDeEmprestimo;
    }

    public Integer getIDEmprestimo() {
        return iDEmprestimo;
    }

    public void setIDEmprestimo(Integer iDEmprestimo) {
        this.iDEmprestimo = iDEmprestimo;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public int getTempoDeEmprestimo() {
        return tempoDeEmprestimo;
    }

    public void setTempoDeEmprestimo(int tempoDeEmprestimo) {
        this.tempoDeEmprestimo = tempoDeEmprestimo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Livro getIDLivro() {
        return iDLivro;
    }

    public void setIDLivro(Livro iDLivro) {
        this.iDLivro = iDLivro;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDEmprestimo != null ? iDEmprestimo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprestimo)) {
            return false;
        }
        Emprestimo other = (Emprestimo) object;
        if ((this.iDEmprestimo == null && other.iDEmprestimo != null) || (this.iDEmprestimo != null && !this.iDEmprestimo.equals(other.iDEmprestimo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Emprestimo[ iDEmprestimo=" + iDEmprestimo + " ]";
    }
    
}
