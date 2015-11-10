'use strict';

/**
 * @ngdoc service
 * @name dashboardApp.maps
 * @description
 * # maps
 * Factory in the dashboardApp.
 */
angular.module('myApp')
  .factory('factoryMaps', function () {
    var markerId = 0;

  function create(latitude, longitude) {
        var marker = {
          coords:{
            latitude: latitude,
            longitude: longitude
          },
            options: {
                animation: 1,
                labelAnchor: "28 -5",
                labelClass: 'markerlabel' //clase css
            },
            id: ++markerId
        };
        return marker;
    }

    function invokeSuccessCallback(successCallback, marker) {
        if (typeof successCallback === 'function') {
            successCallback(marker);
        }
    }

    function createByCoords(latitude, longitude, successCallback) {
        var marker = create(latitude, longitude);
        invokeSuccessCallback(successCallback, marker);
    }

    function createByAddress(address, successCallback) {
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({'address' : address}, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                var firstAddress = results[0];
                var latitude = firstAddress.geometry.location.lat();
                var longitude = firstAddress.geometry.location.lng();
                var marker = create(latitude, longitude);
                invokeSuccessCallback(successCallback, marker);
            } else {
                alert("Unknown address: " + address);
            }
        });
    }

    function createByCurrentLocation(successCallback) {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var marker = create(position.coords.latitude, position.coords.longitude);
                invokeSuccessCallback(successCallback, marker);
            });
        } else {
            alert('Unable to locate current position');
        }
    }

    return {
        createByCoords: createByCoords,
        createByAddress: createByAddress,
        createByCurrentLocation: createByCurrentLocation
    };
  });
