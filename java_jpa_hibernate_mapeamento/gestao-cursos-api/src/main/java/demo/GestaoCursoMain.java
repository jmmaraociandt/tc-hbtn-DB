package demo;

import com.techcamps.gestao.cursos.entities.*;
import com.techcamps.gestao.cursos.models.AlunoModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class GestaoCursoMain {

    public static void main(String[] args) {
        deleteDatabaseIfExists();

        AlunoModel studentModel = new AlunoModel();

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
        Telefone phone2 = new Telefone("(16)", "88888-8888");
        Telefone phone3 = new Telefone("(17)", "77777-7777");

        phoneSet1.add(phone1);
        phoneSet1.add(phone2);

        phoneSet2.add(phone1);

        phoneSet3.add(phone1);
        phoneSet3.add(phone3);

        Aluno student1 = new Aluno("Thor of Asgard", "TC000", LocalDate.of(1022, 12, 12), "thor@email.com", addressSet1, phoneSet1);
        Aluno student2 = new Aluno("Iron Man", "TC001", LocalDate.of(1985, 8, 23), "iron.man@email.com", addressSet2, phoneSet2);
        Aluno student3 = new Aluno("Black Panther", "TC003", LocalDate.of(1987, 4, 9), "panther@email.com", addressSet3, phoneSet3);

//        Professor lecturer = new Professor()
//        Curso course1 = new Curso("Object oriented programming", );

        System.out.println("-----------------------------------------------------------");
        System.out.println("---------------------- STUDENTS TEST ----------------------");
        System.out.println("-----------------------------------------------------------\n");

//        phone1.setAluno(student1);
//        phone2.setAluno(student2);

        studentModel.create(student1);
        studentModel.create(student2);
        studentModel.create(student3);

        List<Aluno> students = studentModel.findAll();
        System.out.println("-----------Find all students: \n");
        students.forEach(System.out::println);

        System.out.println("\n-----------Find a student: \n" + studentModel.findById(student2.getId()));

        Aluno student4 = new Aluno(student2.getId(), "Doctor Strange", "TCXXX", LocalDate.of(1980, 12, 12), "strange@email.com", addressSet3, phoneSet3);
        studentModel.update(student4);
        System.out.println("-----------Find student after update: \n" + studentModel.findById(student2.getId()));

        studentModel.delete(student1);
        students = studentModel.findAll();
        System.out.println("-----------Find all students after deleted:");
        students.forEach(System.out::println);
    }

    private static void deleteDatabaseIfExists() {
        try {
            Files.deleteIfExists(Paths.get("database_admin_jpa.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
