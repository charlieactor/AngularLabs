angular.module("appModule")
	   .factory("todoService", function($http, $filter) {
		   var service = {};
		   
		   var BASE_URL = "http://localhost:8080/ngTodo/api/users/1/todos";
		   
		   service.index = function() {
			   return $http({
				   method  : "GET",
				   url     : BASE_URL
			   });
		   }
		   
		   service.create = function(todo) {
			   todo.completed = false;
			   return $http({
				   method   :  "POST",
				   url      :  BASE_URL,
				   headers  : {
				        'Content-Type' : 'application/json'
				      },
				  data      : todo
			   });
		   };
		   
		   service.update = function(todo) {
			    var id = todo.id;
			    todo.completeDate = "";
			    if (todo.completed == true) {
				    	todo.completeDate = $filter('date')(Date.now(), 'MM/dd/yyyy');
			    } 
			    return $http({
			    		method   :   "PUT",
			    		url      :   BASE_URL + "/" + id,
			    		headers  : {
					        'Content-Type' : 'application/json'
					      },
					  data   : todo
			    })
		   }
		   
		   service.destroy = function(id) {
			   return $http({
				   method  :  "DELETE",
				   url     :  BASE_URL + "/" + id
			   });
		   }
		   
		   return service;
});