/** @type {import('../declarations').BuilderFunction} */
function run(name) {
    const regex = /^[A-Za-zÀ-ÿ\s]+$/;
  
    if (regex.test(name)) {
      const formattedName = name
        .toLowerCase()
        .split(' ')
        .map(word => word.charAt(0).toUpperCase() + word.slice(1))
        .join(' ');
  
      return formattedName;
    }
    else {
      return false;
    }
}