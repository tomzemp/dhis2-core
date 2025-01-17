/*
 * Copyright (c) 2004-2021, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.hisp.dhis.webapi.controller.event;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.junit.MockitoJUnit.rule;

import java.util.Collections;
import java.util.HashSet;

import org.hisp.dhis.common.OrganisationUnitSelectionMode;
import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.dataelement.DataElementService;
import org.hisp.dhis.dxf2.events.event.EventSearchParams;
import org.hisp.dhis.dxf2.util.InputUtils;
import org.hisp.dhis.organisationunit.OrganisationUnit;
import org.hisp.dhis.organisationunit.OrganisationUnitService;
import org.hisp.dhis.program.*;
import org.hisp.dhis.schema.SchemaService;
import org.hisp.dhis.security.acl.AclService;
import org.hisp.dhis.trackedentity.TrackedEntityInstance;
import org.hisp.dhis.trackedentity.TrackedEntityInstanceService;
import org.hisp.dhis.user.CurrentUserService;
import org.hisp.dhis.user.User;
import org.hisp.dhis.user.UserCredentials;
import org.hisp.dhis.webapi.controller.event.mapper.RequestToSearchParamsMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoRule;

/**
 *
 * @author Ameen
 */
public class EventRequestToParamsMapperTest
{
    @Mock
    private CurrentUserService currentUserService;

    @Mock
    private ProgramService programService;

    @Mock
    private OrganisationUnitService organisationUnitService;

    @Mock
    private ProgramStageService programStageService;

    @Mock
    private AclService aclService;

    @Mock
    private TrackedEntityInstanceService entityInstanceService;

    @Mock
    private DataElementService dataElementService;

    @Mock
    private InputUtils inputUtils;

    @Mock
    private SchemaService schemaService;

    @Rule
    public MockitoRule mockitoRule = rule();

    private RequestToSearchParamsMapper requestToSearchParamsMapper;

    @Before
    public void setUp()
    {
        requestToSearchParamsMapper = new RequestToSearchParamsMapper( currentUserService, programService,
            organisationUnitService, programStageService, aclService, entityInstanceService, dataElementService,
            inputUtils, schemaService );

        Program program = new Program();
        User user = new User();
        UserCredentials uc = new UserCredentials();
        user.setUserCredentials( uc );
        OrganisationUnit ou = new OrganisationUnit();
        TrackedEntityInstance tei = new TrackedEntityInstance();
        DataElement de = new DataElement();

        when( currentUserService.getCurrentUser() ).thenReturn( user );
        when( programService.getProgram( any() ) ).thenReturn( program );
        when( organisationUnitService.getOrganisationUnit( any() ) ).thenReturn( ou );

        when( organisationUnitService.isInUserHierarchy( ou ) ).thenReturn( true );
        when( aclService.canDataRead( user, program ) ).thenReturn( true );
        when( entityInstanceService.getTrackedEntityInstance( any() ) ).thenReturn( tei );
        when( dataElementService.getDataElement( any() ) ).thenReturn( de );
    }

    @Test
    public void testEventRequestToSearchParamsMapperSuccess()
    {

        EventSearchParams eventSearchParams = requestToSearchParamsMapper.map( "programuid",
            null, null, null, "orgunituid", OrganisationUnitSelectionMode.ACCESSIBLE, "teiUid",
            null, null, null, null, null,
            null, null, null,
            null, null, null, null,
            false, false, null, null,
            false,
            new HashSet<>(), new HashSet<>(), null,
            null,
            new HashSet<>(), Collections.singleton( "UXz7xuGCEhU:GT:100" ), new HashSet<>(), false,
            false );// Then

        assertThat( eventSearchParams, is( not( nullValue() ) ) );
    }

}
