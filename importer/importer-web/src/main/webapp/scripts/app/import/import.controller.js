angular.module('importerApp').controller('ImportListController',
    ['$scope', '$state', 'Principal', 'ImportService', 'itemsList',  function ($scope, $state, Principal, ImportService, toastr, itemsList ) {

        $scope.delete = function (id) {
            var r = confirm("Do you want to permanently delete this item?");
            if (r == true) {
                ImportService.delete(id).then(function (data) {
                    toastr.success('Item deleted successively');
                    $state.reload();
                });
            }
        };

        $scope.rowListForView = angular.copy(itemsList);

        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });


    }])
    .controller('ImportNewController', function ($scope, $state, $rootScope, toastr, ImportService) {

        $scope.saveFileContent = function (fileContent) {
            $rootScope.fileContent = parseCSV(fileContent);
            var test = 'saf';
        };

        $scope.processForm = function() {
            $rootScope.spinner = true;
            var list = [];
            angular.forEach($rootScope.fileContent, function(row) {
                var temp =  {"mark" : row[0], "model" : row[1], "licencePlate" : row[2]};
                list.push(temp);
            });
            ImportService.create(list).then(function (data) {
                toastr.success('Import added successively');
                $state.go('imports.all');
            });

        };

        function parseCSV(str) {
            var arr = [];
            var quote = false;
            for (var row = col = c = 0; c < str.length; c++) {
                var cc = str[c], nc = str[c+1];
                arr[row] = arr[row] || [];
                arr[row][col] = arr[row][col] || '';

                if (cc == '"' && quote && nc == '"') { arr[row][col] += cc; ++c; continue; }
                if (cc == '"') { quote = !quote; continue; }
                if (cc == ',' && !quote) { ++col; continue; }
                if (cc == ';' && !quote) { ++col; continue; }
                if (cc == '\n' && !quote) { ++row; col = 0; continue; }
                if (cc == '\r' && !quote) {  continue; }

                arr[row][col] += cc;
            }
            return arr;
        }
    });
