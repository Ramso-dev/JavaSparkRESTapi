/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spark.api;

/**
 *
 * @author Ramso
 */
public class Main {

public static void main(String[] args) {
    CorsFilter.apply(); // Call this before mapping thy routes
UserService test = new UserService();
test.createUser("Alice", "email@proton");
test.createUser("Jana", "email@gmail");
new UserController(test);


}

}
