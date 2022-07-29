package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class GestaoCursosMain {

    public static void main(String[] args) {
        deleteDatabaseIfExists();

        AlunoModel studentModel = new AlunoModel();
        CursoModel courseModel = new CursoModel();

        Set<Endereco> addressSet1 = new HashSet<>();
        Set<Endereco> addressSet2 = new HashSet<>();
        Set<Endereco> addressSet3 = new HashSet<>();

        Endereco address1 = new Endereco("Marvel Studios", "Avenue", "100", "MCU", "New York",
                "New York State", 00000000);

        Endereco address2 = new Endereco("Stormbreaker", "Street", "200", "Galaxy Guardian", "Saint Charles",
                "São Paulo", 11111111);

        Endereco address3 = new Endereco("Wakanda", "Street", "300", "Forever", "Woods",
                "Into the woods", 2222222);

        addressSet1.add(address1);
        addressSet1.add(address2);

        addressSet2.add(address1);

        addressSet3.add(address1);
        addressSet3.add(address3);

        Set<Telefone> phoneSet1 = new HashSet<>();
        Set<Telefone> phoneSet2 = new HashSet<>();
        Set<Telefone> phoneSet3 = new HashSet<>();

        Telefone phone1 = new Telefone("(19)", "99999-9999");
        Telefone phone2 = new Telefone("(18)", "88888-8888");
        Telefone phone3 = new Telefone("(17)", "77777-7777");
        Telefone phone4 = new Telefone("(16)", "66666-6666");

        phoneSet1.add(phone1);
        phoneSet1.add(phone2);

        phoneSet2.add(phone1);
        phoneSet2.add(phone2);
        phoneSet2.add(phone4);

        phoneSet3.add(phone1);
        phoneSet3.add(phone3);

        Aluno student1 = new Aluno("Thor of Asgard", "TCS000", LocalDate.of(1022, 12, 12), "thor@email.com");
        Aluno student2 = new Aluno("Iron Man", "TCS001", LocalDate.of(1985, 8, 23), "iron.man@email.com");
        Aluno student3 = new Aluno("Black Panther", "TCS003", LocalDate.of(1987, 4, 9), "panther@email.com");

        student1.setEnderecos(addressSet1);
        student1.setTelefones(phoneSet1);

        student2.setEnderecos(addressSet2);
        student2.setTelefones(phoneSet2);

        student3.setEnderecos(addressSet3);
        student3.setTelefones(phoneSet1);

        MaterialCurso courseContent1 = new MaterialCurso("url1");
        MaterialCurso courseContent2 = new MaterialCurso("url2");
        MaterialCurso courseContent3 = new MaterialCurso("url3");

        Professor lecturer1 = new Professor("Severus Snape", "TCL001", "snape@email.com");
        Professor lecturer2 = new Professor("Albus Dumbledore", "TCL002", "dumbledore@email.com");
        Professor lecturer3 = new Professor("Minerva McGonagall", "TCL003", "minerva@email.com");

        Curso course1 = new Curso("Object oriented programming", "OPP", lecturer1, courseContent1);
//        Curso course2 = new Curso("Data Structure", "DS", lecturer2, courseContent2);
//        Curso course3 = new Curso("Database", "DB", lecturer3, courseContent3);

//        Curso course1 = new Curso();
//
//        course1.setNome("Object oriented programming");
//        course1.setSigla("OPP");
//        course1.setMaterialCurso(courseContent1);
//        course1.setProfessor(lecturer1);

//        course2.setMaterialCurso(courseContent2);
//        course2.setProfessor(lecturer2);
//
//        course3.setMaterialCurso(courseContent3);
//        course3.setProfessor(lecturer3);

        System.out.println("-----------------------------------------------------------");
        System.out.println("---------------------- STUDENTS TEST ----------------------");
        System.out.println("-----------------------------------------------------------\n");

        studentModel.create(student1);
        studentModel.create(student2);
        studentModel.create(student3);

        List<Aluno> students = studentModel.findAll();
        System.out.println("-----------Find all students: \n");
        students.forEach(System.out::println);

        System.out.println("\n-----------Find a student: \n" + studentModel.findById(student2.getId()));

        Aluno student4 = new Aluno(student2.getId(), "Doctor Strange", "TCXXX", LocalDate.of(1980, 12, 12), "strange@email.com");
        student4.setEnderecos(addressSet3);
        student4.setTelefones(phoneSet3);

        studentModel.update(student4);
        System.out.println("-----------Find student after update: \n" + studentModel.findById(student2.getId()));

        studentModel.delete(student1);
        students = studentModel.findAll();
        System.out.println("-----------Find all students after deleted:");
        students.forEach(System.out::println);

        System.out.println("-----------------------------------------------------------");
        System.out.println("---------------------- COURSE TEST ----------------------");
        System.out.println("-----------------------------------------------------------\n");

        courseModel.create(course1);
//        courseModel.create(course2);
//        courseModel.create(course3);
    }

    private static void deleteDatabaseIfExists() {
        try {
            Files.deleteIfExists(Paths.get("database_admin_jpa.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
