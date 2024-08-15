/** @type {import('../declarations').BuilderFunction} */
function run(input) {
    var emailMatch = /[^\s@]+@[^\s@]+\.[^\s@]+/.exec(input.trim());
    return emailMatch && emailMatch[0] ? emailMatch[0] : false;
}