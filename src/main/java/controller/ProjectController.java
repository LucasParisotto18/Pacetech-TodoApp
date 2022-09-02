/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author Lucas
 */
public class ProjectController {
    
    public void save(Project project) {

        String sql = "INSERT INTO projects (name, description, createdAt, updatedAt) VALUES (?, ?, ?, ?)"; // primeio parenteses coloca colunas (do banco de dados) da task 
                                                                                                                      // 8 pontos de interrogação pq sao 8 valores q tem q ser colocados

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            // statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setTimestamp(3, new Timestamp(project.getCreatedAt().getTime()));
            //statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setTimestamp(4, new Timestamp(project.getUpdatedAt().getTime()));
            statement.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar o projeto ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void update(Project project) {

        String sql = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ? ";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());       //setar os valores no statement
            statement.setTimestamp(3, new Timestamp(project.getCreatedAt().getTime()));
            statement.setTimestamp(4, new Timestamp(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());  //id dps do where
            statement.execute();
            
        } catch (SQLException ex) {
        throw new RuntimeException("Erro ao atualizar o projeto.", ex);   
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void removeById(int idProject) { 
        String sql = "DELETE FROM projects WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection(); // criou/chamou a conexão
            statement = connection.prepareStatement(sql); // serve para preparar esse comando sql 
            statement.setInt(1, idProject); // diz que no primeiro (por isso o 1) ponto de interrogação é pra botar o id da task (taskId) que foi recebido por parametro(foi informado pelo usuario)
            statement.execute(); // executa

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar o projeto." , ex);

        } finally {
            ConnectionFactory.closeConnection(connection, statement); // fechar a conexão q eu criei aqui dentro, nesse caso o conn
        }
    }

    public List<Project> getAll() {
       
        String sql = "SELECT * FROM projects";
        
        List<Project> projects = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        // lista de tarefas que vai ser devolvida quando a chamada acontecer
       
        
        try {
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);
           resultSet = statement.executeQuery(); // executeQuery vai executar e vai me returnar um ResultSet
           
           while(resultSet.next()){ // enquanto ainda tiver um proximo/alguma coisa na lista, o while vai ficar rodando
               Project project = new Project();
               
               project.setId(resultSet.getInt("id")); // task vai pegar um int de dentro do result set na coluna "id"
               project.setName(resultSet.getString("name"));
               project.setDescription(resultSet.getString("description"));
               project.setCreatedAt(resultSet.getDate("createdAt"));
               project.setUpdatedAt(resultSet.getDate("updatedAt"));
               projects.add(project);   
           }            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar os projetos.", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return projects; //retorna lista de tarefa criada
    }
}
