
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Lucas
 */
public class TaskController {

    public void save(Task task) {

        String sql = "INSERT INTO tasks (idProject, name, description, completed, notes, deadline," // primeio parenteses coloca colunas (do banco de dados) da task 
                + " createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // 8 pontos de interrogação pq sao 8 valores q tem q ser colocados

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, task.getIdProject()); //setar na posição 1 o idproject
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            /*data do statement é uma data do pacote java.sql e a data que esta no pacote task é uma data do java.util,
                                                                             ai tem que converter, criando uma nova data, buscando o deadline e o get transoforma em long*/
            statement.setTimestamp(7, new Timestamp (task.getCreatedAt().getTime()));
            statement.setTimestamp(8, new Timestamp (task.getUpdatedAt().getTime()));
            
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa. " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void update(Task task) {

        String sql = "UPDATE tasks SET "
                + "idProject = ?, "
                + "name = ?, "
                + "description = ?, "
                + "notes = ?, "
                + "completed = ?, "
                + "deadline = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription()); //setar os valores no statement
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6, new Date (task.getDeadline().getTime()));
            statement.setTimestamp(7, new Timestamp (task.getCreatedAt().getTime()));
            statement.setTimestamp(8, new Timestamp (task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId()); //id dps do where
            statement.execute();
            
        } catch (Exception ex) {
        throw new RuntimeException("Erro ao atualizar a tarefa." + ex.getMessage(), ex);   
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void removeById(int taskId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection(); // criou/chamou a conexão
            statement = connection.prepareStatement(sql); // serve para preparar esse comando sql 
            statement.setInt(1, taskId); // diz que no primeiro (por isso o 1) ponto de interrogação é pra botar o id da task (taskId) que foi recebido por parametro(foi informado pelo usuario)
            statement.execute(); // executa

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa." + ex.getMessage(), ex);

        } finally {
            ConnectionFactory.closeConnection(connection, statement); // fechar a conexão q eu criei aqui dentro, nesse caso o conn
        }
    }

    public List<Task> getAll(int idProject) {
       
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        // lista de tarefas que vai ser devolvida quando a chamada acontecer
        List<Task> tasks = new ArrayList<Task>();
        
        try {
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);
           statement.setInt(1, idProject);
           
           resultSet = statement.executeQuery(); // executeQuery vai executar e vai me returnar um ResultSet
           
           while(resultSet.next()){ // enquanto ainda tiver um proximo/alguma coisa na lista, o while vai ficar rodando
               
               Task task = new Task();
               task.setId(resultSet.getInt("id")); // task vai pegar um int de dentro do result set na coluna "id"
               task.setIdProject(resultSet.getInt("idProject"));
               task.setName(resultSet.getString("name"));
               task.setDescription(resultSet.getString("description"));
               task.setNotes(resultSet.getString("notes"));
               task.setIsCompleted(resultSet.getBoolean("completed"));
               task.setDeadline(resultSet.getDate("deadline"));
               task.setCreatedAt(resultSet.getDate("createdAt"));
               task.setUpdatedAt(resultSet.getDate("updatedAt"));
               tasks.add(task);   
           }         
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir a tarefa." + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
     
        return tasks; //retorna lista de tarefa criada
    }
}
