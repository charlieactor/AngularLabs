angular.module("appModule")
	   .factory("todoService", function() {
		   var service = {};
		   
		   service.index = function() {
			   return todos;
		   }
		   
		   service.create = function(todo) {
			   todos.push(angular.copy(todo));
		   }
		   
		   service.update = function(todo) {
			   var id = todo.id;
				var updatey;
				service.index().forEach(function(val, idx) {
					if (val.id == todo.id) {
						updatey = val;
					}
				});
				updatey.task = todo.task;
				updatey.description = todo.description;
				updatey.completed = todo.completed;
		   }
		   
		   service.destroy = function(id) {
			   service.index().forEach(function(val, idx) {
				   if (id == val.id) {
					   todos.splice(idx, 1);
				   }
			   })
		   }
		   
		   var todos = [
			      {
				        id : 1,
				        task : 'Say "Hmmmmmmm"',
				        description : 'Bother Andy relentlessly',
				        completed : true
				      },
				      {
				        id : 2,
				        task : 'Eat dat lunch',
				        description : 'eat it up yall',
				        completed : false
				      },
				      {
				        id : 3,
				        task : 'Sort life out',
				        description : '',
				        completed :  false
				      }
				    ];
		   
		   return service;
});