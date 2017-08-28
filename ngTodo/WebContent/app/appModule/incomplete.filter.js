angular.module("appModule")
	.filter("incomplete", function() {
		return function(todos, sort) {
			var results = [];
		
		if (sort == false) {
			return todos;
		}
		todos.forEach(function(val) {
			if (val.completed == false) {
				results.push(val);
			}
		});
		return results;
		}
	});