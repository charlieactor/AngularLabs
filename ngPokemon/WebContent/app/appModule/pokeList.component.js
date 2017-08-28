angular.module("appModule")
	   .component("pokeList", {
		   
		   templateUrl : "app/appModule/pokeList.component.html",
		   
		   controller  : function(pokeService) {
			   
			   var vm = this;
			   
			   vm.pokemons = [];
			   
			   vm.sortBy = "all";
			   
			   vm.selected = null;
			   
//			   LOAD ALL POKES FUNCTION
			   
			   var loadDemPokes = function() {
				   var result = pokeService.index();
				   result.then(function(result) {
					    console.log(result.data);
				   		vm.pokemons = result.data;
				   });
			   }
			   
			   loadDemPokes();
			   
//			   CRUD FUNCTIONS
			   
			   vm.showPokemon = function(id) {
				   pokeService.show(id).then(function(result) {
					   vm.selected = result.data;
				   }).catch(function(errors) {
					   console.log(errors);
				   });
			   }
			   
			   vm.deletePoke = function(id) {
				   pokeService.destroy(id);
				   loadDemPokes();
			   }
			   
			   vm.createPokemon = function(newPokemon) {
				   pokeService.create(newPokemon);
				   loadDemPokes();
			   }
			   
		   },
		   
		   controllerAs : "vm"
		   
	   });