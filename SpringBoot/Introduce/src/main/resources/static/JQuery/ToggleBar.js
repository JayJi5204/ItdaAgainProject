$(function () {
  var $aside = $('aside');

  $('#login').click(function () {
    $aside.toggleClass('open');

    if ($aside.hasClass('open')) {
      $aside.css({ transform: 'translateX(-14rem)' });
    } else {
      $aside.css({ transform: 'translateX(0)' }); // 변경된 부분
    }
  });
});
