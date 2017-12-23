const nums = document.querySelectorAll('.nums span');
const counter = document.querySelector('.counter');
const finalMessage = document.querySelector('.final');
const replay = document.getElementById('replay');

runAnimation();

function resetDOM() {
    counter.classList.remove('hide');
    finalMessage.classList.remove('show');

    nums.forEach(function(num){
        num.classList.value = '';
});

    nums[0].classList.add('in');
}

function runAnimation() {
    nums.forEach(function(num, idx){
        var penultimate = nums.length - 1;
    num.addEventListener('animationend', function(e) {
        if(e.animationName === 'goIn' && idx !== penultimate){
        num.classList.remove('in');
        num.classList.add('out');
    } else if (e.animationName === 'goOut' && num.nextElementSibling){
        num.nextElementSibling.classList.add('in');
    } else {
        counter.classList.add('hide');
        finalMessage.classList.add('show');
    }
});
});
}

replay.addEventListener('click', function() {
    resetDOM();
runAnimation();
});