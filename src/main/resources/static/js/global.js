const getData = (url) => {
    return fetch(url)
        .then(response => response.json())
        .catch(err => alert(JSON.stringify(err)));
};

const postData = (url, data) => {
    // Default options are marked with *
    return fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json',
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    })
        .then(response => {
            alert("글이 등록되었습니다.");
            return response.json()
        }) // parses JSON response into native JavaScript objects
        .catch(err => alert(JSON.stringify(err)));
};