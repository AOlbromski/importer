/**
 * INSPINIA - Responsive Admin Theme
 * 2.0
 *
 * Custom scripts
 */

angular.element(document).ready(function () {
   
    // Append config box / Only for demo purpose
    $.get('scripts/app/common/skin-config.html', function (data) {
        angular.element('body').append(data);
    });

    // Full height of sidebar
    function fixHeight() {
        var heightWithoutNavbar = angular.element('body > #wrapper').height() - 61;
        angular.element(".sidebard-panel").css('min-height', heightWithoutNavbar + "px");

        var navbarHeigh = angular.element('nav.navbar-default').height();
        var wrapperHeigh = angular.element('#page-wrapper').height();

        if (navbarHeigh > wrapperHeigh) {
            angular.element('#page-wrapper').css('min-height', navbarHeigh + 'px');
        }

        if (navbarHeigh < wrapperHeigh) {
            angular.element('#page-wrapper').css('min-height', angular.element(window).height() + 'px');
        }

    }


    angular.element(window).bind('load resize scroll', function () {
        if (!angular.element('body').hasClass('body-small')) {
            fixHeight();
        }
    });

    // Move right sidebar top after scroll
    angular.element(window).scroll(function () {
        if (angular.element(window).scrollTop() > 0 && !angular.element('body').hasClass('fixed-nav')) {
            angular.element('#right-sidebar').addClass('sidebar-top');
        } else {
            angular.element('#right-sidebar').removeClass('sidebar-top');
        }
    });


    setTimeout(function () {
        fixHeight();
    });
});

// Minimalize menu when screen is less than 768px
$(function () {
    angular.element(window).bind('load resize', function () {
        if (angular.element(this).width() < 769) {
            angular.element('body').addClass('body-small');
        } else {
            angular.element('body').removeClass('body-small');
        }
    });
});

