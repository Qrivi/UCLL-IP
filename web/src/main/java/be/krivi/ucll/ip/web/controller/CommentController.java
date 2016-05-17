package be.krivi.ucll.ip.web.controller;

import be.krivi.ucll.ip.domain.core.Comment;
import be.krivi.ucll.ip.domain.network.Network;
import be.krivi.ucll.ip.domain.service.NetworkService;
import be.krivi.ucll.ip.web.validation.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Krivi on 15/05/16.
 */

@Controller
@RequestMapping( value = "/comments" )
public class CommentController{

    @Autowired
    private NetworkService service;

    //****************************************************************
    // region RequestMethod.GET
    //****************************************************************

    @RequestMapping( method = RequestMethod.GET, value = "/{id}" )
    public ModelAndView getComments( @PathVariable( "id" ) Integer id ){
        Network network = service.getNetworkById( id );

        ModelMap model = new ModelMap();
        model.addAttribute( "commentform", new CommentDTO() );
        model.addAttribute( "network", network );

        return new ModelAndView( "pages/comments", model );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region RequestMethod.POST
    //****************************************************************

    @RequestMapping( method = RequestMethod.POST, value = "/{id}" )
    public ModelAndView postComment( @PathVariable( "id" ) Integer id, @Valid @ModelAttribute( "commentform" ) CommentDTO commentDTO, BindingResult result, ModelMap model ){
        Network network = service.getNetworkById( id );

        if( result.hasErrors() ){
            model.addAttribute( "network", network );
            return new ModelAndView( "pages/comments", model );
        }

        network.addComment( new Comment( commentDTO.getMessage() ) );
        service.updateNetwork( network );

        return new ModelAndView( "redirect:/comments/" + id );
    }

    //****************************************************************
    // endregion
    //****************************************************************
}
