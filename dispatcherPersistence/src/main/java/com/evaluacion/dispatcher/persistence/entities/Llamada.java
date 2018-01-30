/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evaluacion.dispatcher.persistence.entities;

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
 * @author vtrujillo
 */
@Entity
@Table(name = "llamada",schema="dispacher")
public class Llamada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_call")
    private String idCall;
    @Column(name = "fromcall")
    private String fromcall;
    @Column(name = "tocall")
    private String tocall;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name ="waiting")
    private Boolean waiting;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    @ManyToOne
    private Empleado idEmpleado;

    public Llamada() {
    }

    public Llamada(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCall() {
        return idCall;
    }

    public void setIdCall(String idCall) {
        this.idCall = idCall;
    }

   

    public String getFromcall() {
		return fromcall;
	}

	public void setFromcall(String fromcall) {
		this.fromcall = fromcall;
	}

	public String getTocall() {
		return tocall;
	}

	public void setTocall(String tocall) {
		this.tocall = tocall;
	}

	public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Boolean getWaiting() {
		return waiting;
	}

	public void setWaiting(Boolean waiting) {
		this.waiting = waiting;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Llamada)) {
            return false;
        }
        Llamada other = (Llamada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.evaluacion.dispatcher.entities.Llamada[ id=" + id + " ]";
    }
    
}
