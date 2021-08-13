/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mairo
 */
@Entity
@Table(name = "livro")
@NamedQueries({
    @NamedQuery(name = "Livro.findAll", query = "SELECT l FROM Livro l"),
    @NamedQuery(name = "Livro.findByIDLivro", query = "SELECT l FROM Livro l WHERE l.iDLivro = :iDLivro"),
    @NamedQuery(name = "Livro.findByTitulo", query = "SELECT l FROM Livro l WHERE l.titulo = :titulo"),
    @NamedQuery(name = "Livro.findByCapa", query = "SELECT l FROM Livro l WHERE l.capa = :capa"),
    @NamedQuery(name = "Livro.findByIsbn", query = "SELECT l FROM Livro l WHERE l.isbn = :isbn"),
    @NamedQuery(name = "Livro.findByAutores", query = "SELECT l FROM Livro l WHERE l.autores = :autores"),
    @NamedQuery(name = "Livro.findByQtdexemplares", query = "SELECT l FROM Livro l WHERE l.qtdexemplares = :qtdexemplares"),
    @NamedQuery(name = "Livro.findByQtdemprestados", query = "SELECT l FROM Livro l WHERE l.qtdemprestados = :qtdemprestados")})
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDLivro")
    private Integer iDLivro;
    @Basic(optional = false)
    @Column(name = "Titulo")
    private String titulo;
    @Column(name = "Capa")
    private String capa;
    @Basic(optional = false)
    @Column(name = "ISBN")
    private String isbn;
    @Basic(optional = false)
    @Column(name = "AUTORES")
    private String autores;
    @Basic(optional = false)
    @Column(name = "QTDEXEMPLARES")
    private int qtdexemplares;
    @Basic(optional = false)
    @Column(name = "QTDEMPRESTADOS")
    private int qtdemprestados;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDLivro")
    private Collection<Emprestimo> emprestimoCollection;

    public Livro() {
    }

    public Livro(Integer iDLivro) {
        this.iDLivro = iDLivro;
    }

    public Livro(Integer iDLivro, String titulo, String isbn, String autores, int qtdexemplares, int qtdemprestados) {
        this.iDLivro = iDLivro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autores = autores;
        this.qtdexemplares = qtdexemplares;
        this.qtdemprestados = qtdemprestados;
    }

    public Integer getIDLivro() {
        return iDLivro;
    }

    public void setIDLivro(Integer iDLivro) {
        this.iDLivro = iDLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public int getQtdexemplares() {
        return qtdexemplares;
    }

    public void setQtdexemplares(int qtdexemplares) {
        this.qtdexemplares = qtdexemplares;
    }

    public int getQtdemprestados() {
        return qtdemprestados;
    }

    public void setQtdemprestados(int qtdemprestados) {
        this.qtdemprestados = qtdemprestados;
    }

    public Collection<Emprestimo> getEmprestimoCollection() {
        return emprestimoCollection;
    }

    public void setEmprestimoCollection(Collection<Emprestimo> emprestimoCollection) {
        this.emprestimoCollection = emprestimoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDLivro != null ? iDLivro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) object;
        if ((this.iDLivro == null && other.iDLivro != null) || (this.iDLivro != null && !this.iDLivro.equals(other.iDLivro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Livro[ iDLivro=" + iDLivro + " ]";
    }
    
}
