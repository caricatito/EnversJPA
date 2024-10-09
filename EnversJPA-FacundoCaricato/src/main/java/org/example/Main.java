package org.example;

import Entidades.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            //Creo las entidades para luego hacer los Set y Get
            Factura fac1 = Factura.builder()
                    .build();
            DetalleFactura dfac1 = DetalleFactura.builder()
                    .build();
            DetalleFactura dfac2 = DetalleFactura.builder()
                    .build();

            Categoria cat1 = Categoria.builder()
                    .denominacion("Lacteos")
                    .build();

            Categoria cat2 = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();

            Categoria cat3 = Categoria.builder()
                    .denominacion("Pedecedero")
                    .build();

            Articulo art1 = Articulo.builder()
                    .cantidad(5)
                    .denominacion("Leche")
                    .precio(300)
                    .build();


            Articulo art2 = Articulo.builder()
                    .cantidad(10)
                    .denominacion("Jabon")
                    .precio(250)
                    .build();


            Articulo art3 = Articulo.builder()
                    .cantidad(3)
                    .denominacion("Escoba")
                    .precio(600)
                    .build();


            Articulo art4 = Articulo.builder()
                    .cantidad(8)
                    .denominacion("Yogurt")
                    .precio(100)
                    .build();

            Domicilio dom1 = Domicilio.builder()
                    .nombreCalle("Le Parc")
                    .numero(122)
                    .build();
            Domicilio dom2 = Domicilio.builder()
                    .nombreCalle("Rivadavia")
                    .numero(548)
                    .build();

            Cliente cli1 = Cliente.builder()
                    .nombre("Facundo")
                    .apellido("Caricato")
                    .dni(45023207)
                    .build();
            Cliente cli2 = Cliente.builder()
                    .nombre("Alberto")
                    .apellido("Cortez")
                    .dni(2008853).build();

            dom1.setCliente(cli1);
            dom2.setCliente(cli2);

            cli1.setDomicilio(dom1);
            cli2.setDomicilio(dom2);

            art1.getCategorias().add(cat1);
            art1.getCategorias().add(cat3);
            art2.getCategorias().add(cat2);
            art3.getCategorias().add(cat2);
            art4.getCategorias().add(cat1);
            art4.getCategorias().add(cat3);

            cat1.getArticulos().add(art1);
            cat1.getArticulos().add(art4);
            cat2.getArticulos().add(art2);
            cat2.getArticulos().add(art3);
            cat3.getArticulos().add(art1);
            cat3.getArticulos().add(art4);

            art2.getDetalleFacturas().add(dfac1);

            //Primera versión de factura
            fac1.setCliente(cli1);
            fac1.setFecha("01/09/2024");
            fac1.getDetalleFactura().add(dfac1);
            fac1.getDetalleFactura().add(dfac2);
            dfac1.setFactura(fac1);
            dfac2.setFactura(fac1);

            dfac1.setArticulo(art2);
            dfac1.setCantidad(20);
            dfac1.setSubtotal(100);

            dfac2.setArticulo(art1);
            dfac2.setCantidad(50);
            dfac2.setSubtotal(200);

            fac1.setTotal(300);

            entityManager.persist(fac1);

            entityManager.flush();
            entityManager.getTransaction().commit();

            //Segunda versión de factura
            entityManager.getTransaction().begin();

            fac1.setCliente(cli2);
            fac1.setFecha("12/09/2024");
            fac1.getDetalleFactura().add(dfac1);
            fac1.getDetalleFactura().add(dfac2);
            dfac1.setFactura(fac1);
            dfac2.setFactura(fac1);

            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();

            entityManager.remove(fac1);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar las clases");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}