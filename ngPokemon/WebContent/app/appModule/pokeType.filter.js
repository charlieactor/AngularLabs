angular.module('appModule')
	.filter('pokeType', function() {
		return function(pokemons, type) {
			var newPokemons = [];
			console.log(type);
			if (type === "all") {
				return pokemons;
			}
			else {
				pokemons.forEach(function(val) {
					val.types.forEach(function(poketype) {
						console.log(poketype);
						if(poketype.name == type) {
							newPokemons.push(val);
						};
					});
				});
				return newPokemons;
			}
		}
	});