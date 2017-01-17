/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spark.api;

import static spark.Spark.*;
import static com.mycompany.spark.api.JsonUtil.*;


public class UserController {

	public UserController(final UserService userService) {

		get("/users", (req, res) -> userService.getAllUsers(), json());

		get("/users/:id", (req, res) -> {
			String id = req.params(":id");
			User user = userService.getUser(id);
			if (user != null) {
				return user;
			}
			res.status(400);
			return new ResponseError("No user with id '%s' found", id);
		}, json());

		post("/users", (req, res) -> userService.createUser(
				req.queryParams("name"),
				req.queryParams("email")
		), json());

		put("/users/:id", (req, res) -> userService.updateUser(
				req.params(":id"),
				req.queryParams("name"),
				req.queryParams("email")
		), json());
                
                
                delete("/users/:id", (req,res) -> userService.deleteUser(
				req.params(":id")
				
		), json());

                

		after((req, res) -> {
			res.type("application/json");
		});

		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(400);
			res.body(toJson(new ResponseError(e)));
		});
	}
}
