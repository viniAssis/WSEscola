/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escola.restful;


import com.escola.dao.EstudanteDAO;
import com.escola.model.Status;
import com.escola.model.Estudante;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.BeanParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author vini
 */
@Path("estudante")
public class EstudanteResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EstudanteResource
     */
    public EstudanteResource() {
    }

    /**
     * Retrieves representation of an instance of com.escola.restful.EstudanteResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        
        return "Bem vindo a Api de estudantes";
        
        //throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of EstudanteResource
     * @param content representation for the resource
     */    
    
    @GET
    @Path("{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getUsuarioJson(@PathParam("matricula")String matricula)    {
        Estudante estudante = new EstudanteDAO().getEstudante(matricula);
        
        if(estudante != null)
            return new Gson().toJson(estudante, Estudante.class);
            
        else { 
            Status erro = new Status();
            erro.setStatus("nao encontrado");
            return new Gson().toJson(erro, Status.class);
        }
        
        
    }
    
   
    
    @POST
    @Path("/salvar") 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // recebe do form

    public String salvaEstudante(@BeanParam Estudante estudante){
        


        //Verificação se os campos foram colocar pelo Front-end
        if(estudante.getMatricula() == null || estudante.getNome() == null || estudante.getSobrenome() == null)
            return new Gson().toJson(new Status("verificar parametro(s) nulo(s)"), Status.class);
        
        
        //Pesquisa se já uma matricula cadastrada
     
        
        //Verificação de campos vazios e menos que 3 caracteres 
        if(estudante.getMatricula().equals("") )
            return new Gson().toJson(new Status("Matricula não preenchida"), Status.class);
        
        if(estudante.getMatricula().length() <= 3)
            return new Gson().toJson(new Status("Coloque a matricula com mais de 3 caracteres"), Status.class);
        

        
        if(estudante.getNome().equals(""))
            return new Gson().toJson(new Status("nome não preenchido"), Status.class);
                
        if(estudante.getNome().length() <= 3)
            return new Gson().toJson(new Status("Coloque o nome com mais de 3 caracteres"), Status.class);
        
        
        
        if(estudante.getSobrenome().equals(""))
            return new Gson().toJson(new Status("Sobrenome não preenchido"), Status.class);
        
        if(estudante.getSobrenome().length() <= 3){
            return new Gson().toJson(new Status("Coloque o Sobrenome com mais de 3 caracteres"), Status.class);
        }
        
        //
        
        //Verificação se já há uma matricula cadastrada     
        Estudante matriculaExistente =new EstudanteDAO().getEstudante(estudante.getMatricula());
        
        if(matriculaExistente != null)  
            return new Gson().toJson(new Status("Matricula existente"), Status.class);
        
        //
        
            
        String resp =  new EstudanteDAO().inserirEstudante(estudante);
        
        return new Gson().toJson(new Status(resp), Status.class);
 
    }
    
    @POST
    @Path("/editar") 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // recebe do form 
    public String editarEstudante (@BeanParam Estudante estudante, @FormParam("matriculaNova") String matriculaNova){
        
        String resp = "";
        
        //Verificação se os campos foram colocar pelo Front-end
        
        if(estudante.getMatricula() == null || estudante.getNome() == null || estudante.getSobrenome() == null || matriculaNova == null)
            return new Gson().toJson(new Status("verificar parametro(s) nulo(s)"), Status.class);
        
        
        
        
        //Verificação de campos vazios e menos que 3 caracteres
        
         if(estudante.getMatricula().equals("") )
            return new Gson().toJson(new Status("Matricula não preenchida"), Status.class);
        
        if(estudante.getMatricula().length() <= 3)
            return new Gson().toJson(new Status("Coloque a matricula com mais de 3 caracteres"), Status.class);
        

        
        if(estudante.getNome().equals(""))
            return new Gson().toJson(new Status("nome não preenchido"), Status.class);
                
        if(estudante.getNome().length() <= 3)
            return new Gson().toJson(new Status("Coloque o nome com mais de 3 caracteres"), Status.class);
        
        
        
        if(estudante.getSobrenome().equals(""))
            return new Gson().toJson(new Status("Sobrenome não preenchido"), Status.class);
        
        if(estudante.getSobrenome().length() <= 3){
            return new Gson().toJson(new Status("Coloque o Sobrenome com mais de 3 caracteres"), Status.class);
        }       
        
        
        //Verificação de existência do estudante
        
        Estudante estudanteExiste = new EstudanteDAO().getEstudante(estudante.getMatricula());
               
        if( estudanteExiste != null){          
            resp = new EstudanteDAO().alterarEstudante(estudante, matriculaNova); 
        }
        else{
            resp = "Matricula não existente";
        }
        //
        
        
        
        return new Gson().toJson(new Status(resp), Status.class);
    }
    
    
    @POST
    @Path("/excluir") 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // recebe do form 
    public String excluirEstudante (@FormParam("matricula") String matricula){
     
        if(matricula == null)
            return new Gson().toJson(new Status("verificar parametro(s) nulo(s)"), Status.class);
        //
        
        if(matricula.equals("") )
            return new Gson().toJson(new Status("Matricula não preenchida"), Status.class);
        
        if(matricula.length() <= 3)
            return new Gson().toJson(new Status("Coloque a matricula com mais de 3 caracteres"), Status.class);
        
        //
        
        if(new EstudanteDAO().getEstudante(matricula) == null)
            return new Gson().toJson(new Status("Estudante não existente"), Status.class);      
        
        String resp = new EstudanteDAO().excluirEstudante(matricula);
        
        return new Gson().toJson(new Status(resp), Status.class);
    }
    
}
