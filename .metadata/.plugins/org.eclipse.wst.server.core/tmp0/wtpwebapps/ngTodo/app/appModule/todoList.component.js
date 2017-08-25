angular.module('appModule')
			  .component('todoList', {
				 templateUrl : "app/appModule/todoList.component.html",
				 controller : function(todoService) {
					 var vm = this;
					 
					 vm.selected = null;
					 
					 vm.editTodo = null;
					 
					 vm.setEditTodo = function() {
						 vm.editTodo = angular.copy(vm.selected);
					 };
					 
					 vm.updateTodo = function(todo) {
						todoService.update(todo);
						vm.editTodo = null;
					 };
					 
					 vm.deleteTodo = function(id) {
						 todoService.destroy(id);
					 }
					 
					 vm.displayTable = function() {
						vm.selected = null; 
					 };
					 
					 vm.addTask = function(todo) {
						var generateId = vm.todos[vm.todos.length-1].id + 1;
						todo.id = generateId;
						todo.completed = false;
						todo.description = '';
						
						todoService.create(todo);
					 };
					 
					 vm.todoCount = function() {
						return vm.todos.length; 
					 };
					 
					 vm.displayTodo = function(thing) {
						 vm.selected = thing;
					 }
					 
					 vm.todos = todoService.index();
					 
				 	},
				 	
				 	controllerAs : "vm"
			  });