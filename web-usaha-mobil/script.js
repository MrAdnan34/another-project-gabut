const navClick = (value) => {
    const component = document.getElementById(`content${value}`);
    component.scrollIntoView({
        behavior: 'smooth'
    });

    //         top: component.id,
    //         behavior: "smooth"
    //     })
    // }

}