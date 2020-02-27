/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.model;


import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexandrelerario
 */
@XmlRootElement(name = "lista_alunos")
public class Alunos implements java.io.Serializable{
    private java.util.ArrayList<Aluno> laluno;

    public Alunos(ArrayList<Aluno> laluno) {
        this.laluno = laluno;
    }

    public Alunos() {
    }

   
    @XmlElement(name="aluno")
    public ArrayList<Aluno> getLaluno() {
        return laluno;
    }

    public void setLaluno(ArrayList<Aluno> laluno) {
        this.laluno = laluno;
    }

}
