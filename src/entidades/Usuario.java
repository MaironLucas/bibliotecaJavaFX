/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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

/**
 *
 * @author mairo
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIDUsuario", query = "SELECT u FROM Usuario u WHERE u.iDUsuario = :iDUsuario"),
    @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuario.findByNumDoc", query = "SELECT u FROM Usuario u WHERE u.numDoc = :numDoc"),
    @NamedQuery(name = "Usuario.findByTipoDoc", query = "SELECT u FROM Usuario u WHERE u.tipoDoc = :tipoDoc"),
    @NamedQuery(name = "Usuario.findByTelefone", query = "SELECT u FROM Usuario u WHERE u.telefone = :telefone"),
    @NamedQuery(name = "Usuario.findByStatus", query = "SELECT u FROM Usuario u WHERE u.status = :status"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByMotivo", query = "SELECT u FROM Usuario u WHERE u.motivo = :motivo"),
    @NamedQuery(name = "Usuario.findByFoto", query = "SELECT u FROM Usuario u WHERE u.foto = :foto")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUsuario")
    private Integer iDUsuario;
    @Basic(optional = false)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "NumDoc")
    private String numDoc;
    @Basic(optional = false)
    @Column(name = "TipoDoc")
    private int tipoDoc;
    @Column(name = "Telefone")
    private String telefone;
    @Basic(optional = false)
    @Column(name = "Status")
    private int status;
    @Column(name = "Email")
    private String email;
    @Column(name = "Motivo")
    private Integer motivo;
    @Column(name = "Foto")
    private String foto;
    @JoinColumn(name = "IDEndereco", referencedColumnName = "IDEndereco")
    @ManyToOne
    private Endereco iDEndereco;

    public Usuario() {
    }

    public Usuario(Integer iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Usuario(Integer iDUsuario, String nome, String numDoc, int tipoDoc, int status) {
        this.iDUsuario = iDUsuario;
        this.nome = nome;
        this.numDoc = numDoc;
        this.tipoDoc = tipoDoc;
        this.status = status;
    }

    public Integer getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Integer iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public int getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(int tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMotivo() {
        return motivo;
    }

    public void setMotivo(Integer motivo) {
        this.motivo = motivo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Endereco getIDEndereco() {
        return iDEndereco;
    }

    public void setIDEndereco(Endereco iDEndereco) {
        this.iDEndereco = iDEndereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDUsuario != null ? iDUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.iDUsuario == null && other.iDUsuario != null) || (this.iDUsuario != null && !this.iDUsuario.equals(other.iDUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuario[ iDUsuario=" + iDUsuario + " ]" + "\nNome: " + nome + "\nTipoDoc: " + tipoDoc + "\nNumDoc: " + numDoc + 
                "\nTelefone: " + telefone + "\nStatus: " + status + "\nMotivo: " + motivo + "\nEmail: " + email;
    }
    
}
