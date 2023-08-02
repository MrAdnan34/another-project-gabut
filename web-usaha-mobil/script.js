const navClick = (value) => {
    const component = document.getElementById(`content${value}`);
    component.scrollIntoView({
        behavior: 'smooth'
    });

    //     window.scrollTo({
    //         top: component.id,
    //         behavior: "smooth"
    //     })
    // }

}