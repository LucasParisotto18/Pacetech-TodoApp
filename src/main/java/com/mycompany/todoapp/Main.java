/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.todoapp;

import controller.ProjectController;
import controller.TaskController;
import java.util.Date;
import java.sql.Connection;
import java.util.List;
import model.Project;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Lucas
 */
public class Main {
    
    public static void main(String[] args){
        
      // Connection c = ConnectionFactory.getConnection();
     // ConnectionFactory.closeConnection(c);
         
     
     
                               //Tasks\\
//        TaskController taskController = new TaskController();
//        Task task = new Task();    
        
                                //add nova task\\
//        task.setIdProject(2);
//        task.setName("Teste task1");
//        task.setDescription("desc1");
//        task.setNotes("sem notas");
//        task.setIsCompleted(false);
//        task.setDeadline(new Date());
//        taskController.save(task);
//        
                              //atualizar projeto existente\\
//          task.setId(1);
//          task.setIdProject(2);
//          task.setName("Teste tasknovo1");
//          task.setDescription("desc3");
//          task.setNotes("sem nota");
//          task.setIsCompleted(true);
//          task.setDeadline(new Date());
//          taskController.update(task);

                                //lista tasks\\        
//            List<Task> tasks = taskController.getAll(2); // botar id do projeto nesse caso idproject('2')
//            System.out.println("A quantidade de tarefa(s) existente(s) é(são) = " + tasks.size());           
//                       
                                  //Projetos\\
//       ProjectController projectController = new ProjectController(); 
//       Project project = new Project(); 
//       
       
                        //atualizar projeto existente\\
//        project.setId(4);
//        project.setName("novo teste update");
//        project.setDescription("novo descrição2");
//        project.setCreatedAt(new Date());
//        project.setUpdatedAt(new Date());
//        projectController.update(project);
        
                               //todos os projetos lista\\
//        List<Project> projects = projectController.getAll();
//        System.out.println("A quantidade de projeto(s) existente(s) é(são) = " + projects.size());
//        
        
                               //removebyId\\
//        projectController.removeById(1);
        
                              //add novo projeto\\
//      project.setName("Projeto testes6");
//      project.setDescription("description4");
//      project.setCreatedAt(new Date());
//      project.setUpdatedAt(new Date());
//      projectController.save(project);

          
    }
    
}
