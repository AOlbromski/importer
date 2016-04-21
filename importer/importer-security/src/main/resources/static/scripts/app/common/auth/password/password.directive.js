angular.module('importerApp')
    .directive('softPasswordStrengthBar', function () {
        return {
            replace: true,
            restrict: 'E',
            template: '<div id="strength">' +
            '<small translate="global.messages.validate.newpassword.strength">Password strength:</small>' +
            '<ul id="strengthBar" style="margin-right: 43%">' +
            '<li class="point" ></li><li class="point"></li><li class="point"></li><li class="point"></li><li class="point"></li>' +
            '</ul>' +

            '</div>',
            link: function (scope, iElement, attr) {
                var strength = {
                    colors: ['#F00', '#F90', '#FF0', '#9F0', '#0F0'],
                    mesureStrength: function (p) {

                        var passForce = 0;
                        var passRegex = /[$-/:-?{-~!"^_`\[\]]/g; // "

                        var passLowerLetters = /[a-z]+/.test(p);
                        var passUpperLetters = /[A-Z]+/.test(p);
                        var passNumbers = /[0-9]+/.test(p);
                        var passSymbols = passRegex.test(p);
                        var passPassedMatches = 0;
                        if (passLowerLetters) {
                            passPassedMatches++;
                        }
                        if (passUpperLetters) {
                            passPassedMatches++;
                        }
                        if (passNumbers) {
                            passPassedMatches++;
                        }
                        if (passSymbols) {
                            passPassedMatches++;
                        }
                        passForce += 2 * p.length + ((p.length >= 10) ? 1 : 0);
                        passForce += passPassedMatches * 10;

                        // penality (short password)
                        passForce = (p.length <= 6) ? Math.min(passForce, 10) : passForce;

                        // penality (poor variety of characters)
                        passForce = (passPassedMatches === 1) ? Math.min(passForce, 10) : passForce;
                        passForce = (passPassedMatches === 2) ? Math.min(passForce, 20) : passForce;
                        passForce = (passPassedMatches === 3) ? Math.min(passForce, 40) : passForce;

                        return passForce;

                    },
                    getColor: function (s) {

                        var idx = 0;
                        if (s <= 10) {
                            idx = 0;
                        } else if (s <= 20) {
                            idx = 1;
                        } else if (s <= 30) {
                            idx = 2;
                        } else if (s <= 40) {
                            idx = 3;
                        } else {
                            idx = 4;
                        }

                        return {idx: idx + 1, col: this.colors[idx]};
                    }
                };
                scope.$watch(attr.passwordToCheck, function (password) {
                    if (password) {
                        var c = strength.getColor(strength.mesureStrength(password));
                        iElement.removeClass('ng-hide');
                        iElement.find('ul').children('li')
                            .css({'background': '#DDD'})
                            .slice(0, c.idx)
                            .css({'background': c.col});
                    }
                });
            }
        };
    });
