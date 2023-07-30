const navClick = (value) => {
    const component = document.getElementById(`content${value}`);
    component.scrollIntoView({
        behavior: 'smooth'
    });

    // if (component.id === 'content2') {
    //     console.log('anu2');
    //     window.scrollTo({
    //         top: component.id,
    //         behavior: "smooth"
    //     })
    // }

}