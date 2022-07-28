package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class AdministrativoApp {
    public static void main(String[] args) {
        deleteDatabaseIfExists();

        ProdutoModel productModel = new ProdutoModel();
        PessoaModel personModel = new PessoaModel();

        Produto product1 = new Produto();
        Produto product2 = new Produto();
        Produto product3 = new Produto();
        Produto product4 = new Produto();

        Pessoa person1 = new Pessoa();
        Pessoa person2 = new Pessoa();
        Pessoa person3 = new Pessoa();
        Pessoa person4 = new Pessoa();

        product1.setNome("TV");
        product1.setPreco(300.0);
        product1.setQuantidade(100);
        product1.setStatus(true);

        product2.setNome("Celular");
        product2.setPreco(1850.80);
        product2.setQuantidade(1);
        product2.setStatus(true);

        product3.setNome("Computador");
        product3.setPreco(5431.23);
        product3.setQuantidade(2);
        product3.setStatus(false);

        product4.setId(3);
        product4.setNome("Computador");
        product4.setPreco(5000.00);
        product4.setQuantidade(2);
        product4.setStatus(true);

        person1.setNome("Hulk");
        person1.setEmail("smash@email.com");
        person1.setIdade(37);
        person1.setCpf("000.000.000-00");
        person1.setDataNascimento(LocalDate.of(1985, 7, 23));

        person2.setNome("Scarlet Witch");
        person2.setEmail("scarlet@email.com");
        person2.setIdade(32);
        person2.setCpf("111.111.111-11");
        person2.setDataNascimento(LocalDate.of(1990, 1, 1));

        person3.setNome("Thor");
        person3.setEmail("thor@email.com");
        person3.setIdade(1000);
        person3.setCpf("222.222.222-22");
        person3.setDataNascimento(LocalDate.of(1022, 12, 12));

        person4.setId(3);
        person4.setNome("Black Widow");
        person4.setEmail("widow@email.com");
        person4.setIdade(29);
        person4.setCpf("333.333.333-33");
        person4.setDataNascimento(LocalDate.of(1993, 4, 19));

        System.out.println("-----------------------------------------------------------");
        System.out.println("------------------ TESTES DE PRODUTOS ---------------------");
        System.out.println("-----------------------------------------------------------\n");

        // 1) Criando um produto
        productModel.create(product1);
        productModel.create(product2);
        productModel.create(product3);

        //2) Buscando todos os produtos na base de dados
        List<Produto> produtos = productModel.findAll();


        System.out.println("-----------Buscar todos produtos: \n");
        produtos.forEach(System.out::println);
        System.out.println("-----------");
        System.out.println("Qtde de produtos encontrados: " + produtos.size());

        //3) Buscar por Produto
        System.out.println("\n-----------Buscar por produto: " + productModel.findById(product3));

        //3) Editar produto
        productModel.update(product4);
        System.out.println("\n-----------Buscar por produto depois de atualizar: " + productModel.findById(product3));

        //4) Deletar produto
        productModel.delete(product2);
        produtos = productModel.findAll();
        System.out.println("-----------Buscar todos produtos depois de deletar: \n");
        produtos.forEach(System.out::println);
        System.out.println("-----------");
        System.out.println("Qtde de produtos encontrados: " + produtos.size());

        System.out.println("-----------------------------------------------------------");
        System.out.println("------------------ TESTES DE PESSOAS ---------------------");
        System.out.println("-----------------------------------------------------------\n");

        // 1) Criando uma pessoa
        personModel.create(person1);
        personModel.create(person2);
        personModel.create(person3);

        //2) Buscando todas pessoas na base de dados
        List<Pessoa> people = personModel.findAll();


        System.out.println("-----------Buscar todas pessoas: \n");
        people.forEach(System.out::println);
        System.out.println("-----------");
        System.out.println("Qtde de pessoas encontradas: " + people.size());

        //3) Buscar por Pessoa
        System.out.println("\n-----------Buscar por pessoa: " + personModel.findById(person3));

        //3) Editar pessoa
        personModel.update(person4);
        System.out.println("\n-----------Buscar por pessoa depois de atualizar: " + personModel.findById(person3));

        //4) Deletar pessoa
        personModel.delete(person2);
        people = personModel.findAll();
        System.out.println("-----------Buscar todas pessoas depois de deletar: \n");
        people.forEach(System.out::println);
        System.out.println("-----------");
        System.out.println("Qtde de pessoas encontradas: " + people.size());
    }

    private static void deleteDatabaseIfExists() {
        try {
            Files.deleteIfExists(Paths.get("database_admin.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
