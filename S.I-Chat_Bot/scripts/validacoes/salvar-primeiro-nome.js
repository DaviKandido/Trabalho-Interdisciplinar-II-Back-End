/** @type {import('../declarations').BuilderFunction} */
function run(input) {
    if (typeof input !== 'string' || /\d/.test(input)) {
        return false;
    }

    var words = input.trim().split(' ').filter(Boolean);
    var firstName = words[0];

    return firstName.charAt(0).toUpperCase() + firstName.slice(1);
}