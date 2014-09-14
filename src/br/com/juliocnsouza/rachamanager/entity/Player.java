package br.com.juliocnsouza.rachamanager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player implements Serializable, Comparable<Player> {

    private static final long serialVersionUID = 1L;


    @Id
    private String nome;

    @Column
    private float goalKeeperSkills;

    @Column
    private float defenseSkills;

    @Column
    private float attackSkills;

    public Player() {
        this.attackSkills = 0.0f;
        this.defenseSkills = 0.0f;
        this.goalKeeperSkills = 0.0f;
        this.nome = "no name";
    }



    public Player(final String nome, final float goalKeeperSkills, final float defenseSkills,
                    final float attackSkills) {
        super();
        this.nome = nome;
        this.goalKeeperSkills = goalKeeperSkills;
        this.defenseSkills = defenseSkills;
        this.attackSkills = attackSkills;
        if ((nome == null) || nome.isEmpty()) this.nome = "no name";

    }



    public String getNome() {
        return this.nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public float getGoalKeeperSkills() {
        return this.goalKeeperSkills;
    }

    public void setGoalKeeperSkills(final float goalKeeperSkills) {
        this.goalKeeperSkills = goalKeeperSkills;
    }

    public float getDefenseSkills() {
        return this.defenseSkills;
    }

    public void setDefenseSkills(final float defenseSkills) {
        this.defenseSkills = defenseSkills;
    }

    public float getAttackSkills() {
        return this.attackSkills;
    }

    public void setAttackSkills(final float attackSkills) {
        this.attackSkills = attackSkills;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + (this.nome == null ? 0 : this.nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final Player other = (Player) obj;
        if (this.nome == null) {
            if (other.nome != null) return false;
        } else if (!this.nome.equals(other.nome)) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.nome.toUpperCase();
    }



    @Override
    public int compareTo(final Player arg0) {
        return this.nome.compareTo(arg0.nome);
    }

}
