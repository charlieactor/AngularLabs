angular.module('appModule')
			  .component('todoList', {
				 templateUrl : "app/appModule/todoList.component.html",
				 controller : function(todoService, $filter) {
					 var vm = this;
					 
					 vm.selected = null;
					 
					 vm.editTodo = null;
					 
					 vm.incompleteSort = false;
					 
					 vm.crossOut = function(todo) {
						 console.log(todo.completed);
						 if (todo.completed) {
							 return "crossed";
						 } else {
							 return "normal";
						 }
					 };
					 
					 vm.incompleteCount = function() {
						 var count = $filter('incomplete')(vm.todos, true).length;
						 if (count >= 10) {
							 return "danger";
						 }
						 else if (count >=5) {
							 return "warning";
						 }
						 else {
							 return "safe";
						 }
					 }
					 
					 vm.setEditTodo = function() {
						 vm.editTodo = angular.copy(vm.selected);
					 };
					 
					 vm.updateTodo = function(todo, change) {
						todoService.update(todo).then(function(result) {
							if (change != 'stay') {
								vm.selected = result.data;
							}
							vm.reload();
						})
						vm.editTodo = null;
					 };
					 
					 vm.deleteTodo = function(id) {
						 todoService.destroy(id)
						 		    .then(function(result) {
						 		    		vm.reload();
						 		    })
					 }
					 
					 vm.displayTable = function() {
						vm.selected = null; 
					 };
					 
					 vm.addTask = function(todo) {
						todoService.create(todo).then(
								function(result) {
									vm.reload();
								})
					 };
					 
					 vm.todoCount = function() {
						 return $filter('incomplete')(vm.todos, true).length;
					 };
					 
					 vm.displayTodo = function(thing) {
						 vm.selected = thing;
					 }
					 
					 vm.reload = function() {
						todoService.index().then(function(result) {
							vm.todos = result.data;
						}); 
						
					 };
					 
					 vm.reload();
					 
				 	},
				 	
				 	controllerAs : "vm"
			  });