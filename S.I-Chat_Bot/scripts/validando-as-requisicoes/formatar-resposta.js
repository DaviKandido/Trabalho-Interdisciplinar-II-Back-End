function run(responseAI) {
    let formattedResponse = responseAI
        .replace(/\*\*(.*?)\*\*/g, '<b>$1</b>')
        .replace(/\n/g, '<br>')
        .replace(/\*(.*?)\:/g, '- $1:')
        .replace(/\* /g, '- ')

    return formattedResponse;
}