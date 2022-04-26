function validAnagram(string1, string2) {
    //checks that both strings are the same length
    if (string1.length !== string2.length) {
        return false;
    }
    //container to hold letters to count.
    const counter = {};
    //counting letters in string1
    for (let i = 0; i < string1.length; i++) {
        let letter = string1[i];
        // if letter exists, increment, otherwise set to 1
        counter[letter] ? counter[letter] += 1 : counter[letter] = 1;
    }
    console.log(counter)

    //comparing string2 to string1
    for (let i = 0; i < string2.length; i++) {
        let letter = string2[i];


        if (!counter[letter]) {
            return false;
        } else {
            counter[letter] -= 1;
        }
    }

    return true;
}