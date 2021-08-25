/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import exceptions.ExceptionGenerica;
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
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIDUsuario", query = "SELECT u FROM Usuario u WHERE u.iDUsuario = :iDUsuario"),
    @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome like :nome"),
    @NamedQuery(name = "Usuario.findByNumDoc", query = "SELECT u FROM Usuario u WHERE u.numDoc = :numDoc"),
    @NamedQuery(name = "Usuario.findByTipoDoc", query = "SELECT u FROM Usuario u WHERE u.tipoDoc = :tipoDoc"),
    @NamedQuery(name = "Usuario.findByTelefone", query = "SELECT u FROM Usuario u WHERE u.telefone = :telefone"),
    @NamedQuery(name = "Usuario.findByStatus", query = "SELECT u FROM Usuario u WHERE u.status = :status"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByMotivo", query = "SELECT u FROM Usuario u WHERE u.motivo = :motivo"),
    @NamedQuery(name = "Usuario.findByFoto", query = "SELECT u FROM Usuario u WHERE u.foto = :foto"),
    @NamedQuery(name = "Usuario.findByCidade", query = "SELECT u FROM Usuario u WHERE u.cidade = :cidade"),
    @NamedQuery(name = "Usuario.findByUf", query = "SELECT u FROM Usuario u WHERE u.uf = :uf"),
    @NamedQuery(name = "Usuario.findByCep", query = "SELECT u FROM Usuario u WHERE u.cep = :cep"),
    @NamedQuery(name = "Usuario.findByRua", query = "SELECT u FROM Usuario u WHERE u.rua = :rua"),
    @NamedQuery(name = "Usuario.findByBairro", query = "SELECT u FROM Usuario u WHERE u.bairro = :bairro"),
    @NamedQuery(name = "Usuario.findByNumero", query = "SELECT u FROM Usuario u WHERE u.numero = :numero")})
public class Usuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDUsuario")
    private Collection<Emprestimo> emprestimoCollection;

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
    @Column(name = "Cidade")
    private String cidade;
    @Column(name = "UF")
    private String uf;
    @Column(name = "CEP")
    private String cep;
    @Column(name = "Rua")
    private String rua;
    @Column(name = "Bairro")
    private String bairro;
    @Column(name = "Numero")
    private String numero;

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

    public void setNome(String nome) throws ExceptionGenerica{
        if (nome.isBlank() || nome.isEmpty())
            throw new ExceptionGenerica("Campo nome não informado!");
        else
            this.nome = nome;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) throws ExceptionGenerica{
        if (numDoc.isBlank() || numDoc.isEmpty())
            throw new ExceptionGenerica("Número de documento não foi informado!");
        else{
            try{
                Long temp = Long.parseLong(numDoc);
                this.numDoc = numDoc;
            } catch (NumberFormatException e){
                throw new ExceptionGenerica("Número de documento só aceita números!");
            }
        }
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

    public void setTelefone(String telefone) throws ExceptionGenerica{
        if (telefone.isBlank() || telefone.isEmpty())
            throw new ExceptionGenerica("Telefone não foi informado!");
        else{
            try{
                Long temp = Long.parseLong(telefone);
                this.telefone = telefone;
            } catch (NumberFormatException e){
                throw new ExceptionGenerica("Telefone só aceita números!");
            }
        } 
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) throws ExceptionGenerica{
        if(cidade.isBlank() || cidade.isEmpty())
            throw new ExceptionGenerica("Cidade precisa ser informada!");
        else
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

    public void setCep(String cep) throws ExceptionGenerica{
        if(cep.isBlank() || cep.isEmpty())
            throw new ExceptionGenerica("CEP precisa ser informado!");
        else
            this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) throws ExceptionGenerica{
        if(rua.isBlank() || rua.isEmpty())
            throw new ExceptionGenerica("Rua precisa ser informada!");
        else
            this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) throws ExceptionGenerica{
        if(bairro.isBlank() || bairro.isEmpty())
            throw new ExceptionGenerica("Bairro precisa ser informado!");
        else
            this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) throws ExceptionGenerica{
        if(numero.isBlank() || numero.isEmpty())
            throw new ExceptionGenerica("Numero precisa ser informado!");
        else{
            try{
                Long temp = Long.parseLong(numero);
                this.numero = numero;
            } catch (NumberFormatException e){
                throw new ExceptionGenerica("Numero não pode conter caracteres!");
            }
        }    
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
        return "entidades.Usuario[ iDUsuario=" + iDUsuario + " ]";
    }

    public Collection<Emprestimo> getEmprestimoCollection() {
        return emprestimoCollection;
    }

    public void setEmprestimoCollection(Collection<Emprestimo> emprestimoCollection) {
        this.emprestimoCollection = emprestimoCollection;
    }
    
}
