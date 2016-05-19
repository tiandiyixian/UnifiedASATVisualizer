var colorScale = (function() {

	// default color settings
	var color = d3.scale.linear().domain([0, 100]).interpolate(d3.interpolateHcl).range([d3.rgb("#00C800"), d3.rgb('#C80000')]);

	/*
	 *
	 * Calculates the worst case of #warnings/loc
	 *
	 */ 
	function getRelativeWarnings() {
		var worstRatio = 0;
		for(var p =0; p < inputData.length; p++){
			var package = inputData[p];
			var classesArray = package.classes;
			for (i = 0; i < classesArray.length; i++) {
				classObjectJson = classesArray[i];
				var numberOfWarnings = 0;
				for (j = 0; j < classObjectJson.warningList.length; j++) { 
					numberOfWarnings++;
		  		}
		  		var curRatio = numberOfWarnings / classObjectJson.loc;
		  		if(curRatio > worstRatio) {
		  			worstRatio = curRatio;
		  		}
			}
		}
		return (worstRatio * 100);
	}

    return {

        getColor: function() {
            return color;
        },
		getColor: function(ratio) {
            return (ratio > 100) ? color(100) : color(ratio);
        },
		colorsRelative: function() {
			color = d3.scale.linear().domain([0, getRelativeWarnings()]).interpolate(d3.interpolateHcl).range([d3.rgb("#00C800"), d3.rgb('#C80000')]);
		},
		colorsAbsolute: function() {
			color = d3.scale.linear().domain([0, 100]).interpolate(d3.interpolateHcl).range([d3.rgb("#00C800"), d3.rgb('#C80000')]);
		}

    };

}());
        