/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "endereco")
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e"),
    @NamedQuery(name = "Endereco.findByIDEndereco", query = "SELECT e FROM Endereco e WHERE e.iDEndereco = :iDEndereco"),
    @NamedQuery(name = "Endereco.findByCidade", query = "SELECT e FROM Endereco e WHERE e.cidade = :cidade"),
    @NamedQuery(name = "Endereco.findByUf", query = "SELECT e FROM Endereco e WHERE e.uf = :uf"),
    @NamedQuery(name = "Endereco.findByCep", query = "SELECT e FROM Endereco e WHERE e.cep = :cep"),
    @NamedQuery(name = "Endereco.findByRua", query = "SELECT e FROM Endereco e WHERE e.rua = :rua"),
    @NamedQuery(name = "Endereco.findByBairro", query = "SELECT e FROM Endereco e WHERE e.bairro = :bairro")})
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEndereco")
    private Integer iDEndereco;
    @Basic(optional = false)
    @Column(name = "Cidade")
    private String cidade;
    @Basic(optional = false)
    @Column(name = "UF")
    private String uf;
    @Basic(optional = false)
    @Column(name = "CEP")
    private String cep;
    @Basic(optional = false)
    @Column(name = "Rua")
    private String rua;
    @Basic(optional = false)
    @Column(name = "Bairro")
    private String bairro;
    @OneToMany(mappedBy = "iDEndereco")
    private Collection<Usuario> usuarioCollection;

    public Endereco() {
    }

    public Endereco(Integer iDEndereco) {
        this.iDEndereco = iDEndereco;
    }

    public Endereco(Integer iDEndereco, String cidade, String uf, String cep, String rua, String bairro) {
        this.iDEndereco = iDEndereco;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
    }

    public Integer getIDEndereco() {
        return iDEndereco;
    }

    public void setIDEndereco(Integer iDEndereco) {
        this.iDEndereco = iDEndereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDEndereco != null ? iDEndereco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.iDEndereco == null && other.iDEndereco != null) || (this.iDEndereco != null && !this.iDEndereco.equals(other.iDEndereco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Endereco[ iDEndereco=" + iDEndereco + " ]";
    }
    
}
